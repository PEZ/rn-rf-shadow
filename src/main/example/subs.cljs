(ns example.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :get-counter
 (fn [db _]
   (:counter db)))

(rf/reg-sub
 :counter-tappable?
 (fn [db _]
   (:counter-tappable? db)))

(rf/reg-sub
 :routing/current-route
 (fn [db _]
   (get-in db [:routing :current-route])))

(rf/reg-sub
 :routing/navigation-root-state
 (fn [db _]
   (get-in db [:routing :navigation-root-state])))
