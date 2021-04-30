(ns example.db
  (:require [re-frame.core :as rf]))

;; initial state of app-db
(def app-db {:counter 0
                 :counter-tappable? true
                 :last-fibs [0 1]})

(comment
  @re-frame.db/app-db)