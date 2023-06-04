(ns example.events
  (:require
   [re-frame.core :as rf]
   [example.db :as db :refer [app-db]]))


(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   app-db))

(rf/reg-event-db
 :inc-counter
 (fn [db [_ _]]
   (update db :counter inc)))

(rf/reg-fx
 :routing/navigate
 (fn [[navigation route]]
   (.navigate navigation route)))

(rf/reg-event-db
 :routing/set-current-route
 (fn [db [_ route]]
   (assoc-in db [:routing :current-route] route)))

(rf/reg-event-fx
 :routing/navigate
 (fn [_ [_ navigation route]]
   {:dispatch [:routing/set-current-route route]
    :routing/navigate [navigation route]}))

(rf/reg-event-db
 :routing/set-navigation-root-state
 (fn [db [_ navigation-root-state]]
   (assoc-in db [:routing :navigation-root-state] navigation-root-state)))
