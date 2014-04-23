(ns pot.core.comp
  (:require [cljs.core.async :refer [chan]]
            [pot.core.bl :refer [make-board add-random-cells]])
  (:import [goog.storage Storage]
           [goog.storage.mechanism HTML5LocalStorage]))

(defn init-state
  {:pre [(<= init (* height width))]}
  [{:keys [height width init]}]
  {:board     (add-random-cells init (make-board height width))
   :direction nil})

(defn create-state
  [init-data]
  (-> init-data init-state atom))

(defn create-channels
  []
  {:actions (chan)})

(defn init-history
  []
  {:snapshots []
   :cursor    0})

(defn create-history
  []
  (atom (init-history)))

(defn create-storage
  []
  (Storage. (HTML5LocalStorage.)))