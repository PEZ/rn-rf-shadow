(ns example.db
  (:require [re-frame.core :as rf]))

;; initial state of app-db
(defonce app-db {:counter 0
                 :counter-tappable? true
                 :last-fibs [1 1]})

(comment
  @re-frame.db/app-db)