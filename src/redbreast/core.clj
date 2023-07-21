(ns redbreast.core
  (:require [clojure.core.async :refer [<!!]]
            [clojure.string :as s]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t])
  (:gen-class))

(def token (env :telegram-token))

(h/defhandler handler

  (h/command-fn "start"
    (fn [{{id :id :as chat} :chat}]
      (println "Bot joined new chat: " chat)
      (t/send-text token id "Welcome to redbreast!")))

  (h/command-fn "help"
    (fn [{{id :id :as chat} :chat}]
      (println "Help was requested in " chat)
      (t/send-text token id "Help is on the way")))

  (h/message-fn
    (fn [{{id :id} :chat :as message}]
      (println "Intercepted message: " message)
      (t/send-text token id "I don't do a whole lot ... yet."))))

(defn -main
  [& args]
  (when (s/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting redbreast")
  (<!! (p/start token handler)))
