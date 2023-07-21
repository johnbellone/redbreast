(ns redbreast.core
  (:require [clojure.string :as s]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.api :as t]
            [ring.adapter.jetty9 :refer [run-jetty]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route])
  (:gen-class))

(def port (env :port))
(def token (env :telegram-token))

(h/defhandler bot-api
  "Define the Telegram webhook that will receive commands from the API."
  (h/command-fn "start"
                (fn [{{id :id :as chat} :chat}]
                  (println "Bot joined new chat: " chat)
                  (t/send-text token id "Welcome to redbreast!")))

  (h/command-fn "help"
                (fn [{{id :id :as chat} :chat}]
                  (println "Help was requested in " chat)
                  (t/send-text token id "Help is on the way")))

  ; This is generic catch for any messages that we do not know how to handle.
  (h/message-fn
   (fn [{{id :id} :chat :as message}]
     (println "Intercepted message: " message)
     (t/send-text token id "I don't do a whole lot ... yet."))))

(defroutes app-routes
  (GET "/" [] "running")
  (POST "/handler" {body :body} (bot-api body))
  (route/not-found "Not Found"))

(defn -main
  [& _args]
  (when (s/blank? token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))
  (println "Starting redbreast")
  (run-jetty app-routes {:port 8090 :join? true}))
