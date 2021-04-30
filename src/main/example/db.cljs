(ns example.db
  (:require [re-frame.core :as rf]))

;; initial state of app-db
(def app-db {:counter 0
             :counter-tappable? true})

(comment
  @re-frame.db/app-db)