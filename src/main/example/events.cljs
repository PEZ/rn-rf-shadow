(ns example.events
  (:require
   [re-frame.core :refer [reg-event-db dispatch subscribe]]
   [example.db :refer [app-db]]))

(reg-event-db
 :initialize-db
 (fn [_ _]
   app-db))

(reg-event-db
 :inc-counter
 (fn [db [_ _]]
   (update db :counter (comp inc inc inc))))

(reg-event-db
 :set-tap-enabled
 (fn [db [_ enabled?]]
   (assoc db :counter-tappable? enabled?)))

(reg-event-db
 :advance-fib
 (fn [db [_ _]]
   (update db :last-fibs (fn [[fib1 fib2]]
                           [fib2 (+ fib1 fib2)]))))

