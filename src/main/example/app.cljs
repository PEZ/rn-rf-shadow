(ns example.app
  (:require
   ["react-native" :as rn]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [expo.root :as expo-root]
   [example.events]
   [example.subs]))

;; must use defonce and must refresh full app so metro can fill these in
;; at live-reload time `require` does not exist and will cause errors
;; must use path relative to :output-dir

(defonce splash-img (js/require "../assets/shadow-cljs.png"))

(def styles
  {:container   {:flex             1
                 :background-color :white
                 :align-items      :center
                 :justify-content  :flex-start
                 :padding-top      50}
   :counter     {:font-weight   :bold
                 :font-size     24
                 :color         :blue
                 :margin-bottom 20}
   :button      {:font-weight      :bold
                 :font-size        18
                 :padding          6
                 :background-color :blue
                 :border-radius    999
                 :margin-bottom    20}
   :button-text {:padding-left  12
                 :padding-right 12
                 :font-weight   :bold
                 :font-size     18
                 :color         :white}
   :logo        {:width  200
                 :height 200}
   :creds       {:font-weight :normal
                 :font-size   15
                 :color       :blue}})

(defn root []
  [:> rn/View {:style (:container styles)}
   [:> rn/Text {:style (:counter styles)} "Clicked: " @(rf/subscribe [:get-counter])]
   [:> rn/TouchableOpacity {:style    (:button styles)
                            :on-press #(rf/dispatch [:inc-counter])}
    [:> rn/Text {:style (:button-text styles)} "Click me, I'll count!!!!"]]
   [:> rn/Image {:style  (:logo styles)
                 :source splash-img}]
   [:> rn/Text {:style (:creds styles)} "Using: shadow-cljs+expo+reagent+re-frame"]])

(defn start
  {:dev/after-load true}
  []
  (expo-root/render-root (r/as-element [root])))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (start))