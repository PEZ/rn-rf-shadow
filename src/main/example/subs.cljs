(ns example.subs
  (:require [re-frame.core :refer [reg-sub dispatch subscribe]]))

(reg-sub
 :get-counter
 (fn [db _]
   (:counter db)))

(reg-sub
 :counter-tappable?
 (fn [db _]
   (:counter-tappable? db)))

(reg-sub
 :current-fib
 (fn [db _]
   (->> db
        :last-fibs
        first)))

(->> @re-frame.db/app-db
     :last-fibs)