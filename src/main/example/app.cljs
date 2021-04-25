(ns example.app
  (:require
   ["react-native" :as rn]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [shadow.expo :as expo]
   [example.events]
   [example.subs]))

(defonce splash-img (js/require "../assets/shadow-cljs.png"))

(defn- button [{:keys [style text-style on-press
                       disabled? disabled-style disabled-text-style]
                :or {on-press #()}} text]
  [:> rn/TouchableOpacity {:style (cond-> {:font-weight      :bold
                                           :font-size        18
                                           :padding          6
                                           :background-color :blue
                                           :border-radius    999
                                           :margin-bottom    20}
                                    :always (merge style)
                                    disabled? (merge {:background-color "#aaaaaa"}
                                                     disabled-style))
                           :on-press on-press
                           :disabled disabled?}
   [:> rn/Text {:style (cond-> {:padding-left  12
                                :padding-right 12
                                :font-weight   :bold
                                :font-size     18
                                :color         :white}
                         :always (merge text-style)
                         disabled? (merge {:color :white}
                                          disabled-text-style))} 
    text]])

(defn root []
  (let [counter @(rf/subscribe [:get-counter])
        tap-enabled? @(rf/subscribe [:counter-tappable?])]
    [:> rn/View {:style {:flex             1
                         :background-color :white
                         :align-items      :center
                         :justify-content  :flex-start
                         :padding-vertical 50}}
     [:> rn/Text {:style {:font-weight   :bold
                          :font-size     24
                          :color         :blue
                          :margin-bottom 20}} "Clicked: " counter]
     [button {:on-press #(rf/dispatch [:inc-counter])
              :disabled? (not tap-enabled?)
              :style {:background-color :blue}}
      "Click me, I'll count"]
     [:> rn/Image {:style {:width  200
                           :height 200}
                   :source splash-img}]
     [:> rn/Text {:style {:font-weight :normal
                          :font-size   15
                          :color       :blue}}
      "Using: shadow-cljs+expo+reagent+re-frame"]]))

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