(ns example.widgets
  (:require ["react-native" :as rn]))


(defn button [{:keys [style text-style on-press
                       disabled? disabled-style disabled-text-style]
                :or {on-press #()}} text]
  [:> rn/Pressable {:style (cond-> {:font-weight      :bold
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