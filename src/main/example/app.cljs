(ns example.app
  (:require [example.events]
            [example.subs]
            [example.widgets :refer [button]]
            [re-frame.core :as rf]
            ["react-native" :as rn]
            [reagent.core :as r]
            [shadow.expo :as expo]))

(def shadow-splash (js/require "../assets/shadow-cljs.png"))
(def cljs-splash (js/require "../assets/cljs.png"))

(defn root []
  (let [counter @(rf/subscribe [:get-counter])
        tap-enabled? @(rf/subscribe [:counter-tappable?])]
    [:> rn/View {:style {:flex 1
                         :padding-vertical 50
                         :justify-content :flex-start
                         :align-items :center
                         :background-color :white}}
     [:> rn/View {:style {:align-items :center}}
      [button {:on-press #(rf/dispatch [:inc-counter])
               :disabled? (not tap-enabled?)
               :style {:background-color :blue}}
       "Tap me, I'll count"]
      [:> rn/Text {:style {:font-weight   :bold
                           :font-size     24
                           :color         :blue
                           :margin-bottom 20}} "Tapped: " counter]]
     [:> rn/View
      [:> rn/View {:style {:flex-direction :row
                           :align-items :center
                           :margin-bottom 20}}
       [:> rn/Image {:style {:width  160
                             :height 160}
                     :source cljs-splash}]
       [:> rn/Image {:style {:width  160
                             :height 160}
                     :source shadow-splash}]]
      [:> rn/Text {:style {:font-weight :normal
                           :font-size   15
                           :color       :blue}}
       "Using: shadow-cljs+expo+reagent+re-frame"]]]))

(defn start
  {:dev/after-load true}
  []
  (expo/render-root (r/as-element [root])))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (start))

(comment
  (rf/dispatch [:set-tap-enabled true])
  (rf/dispatch [:inc-counter]))