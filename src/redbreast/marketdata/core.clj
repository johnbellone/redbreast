(ns redbreast.marketdata.core
  (:require [clojure.tools.logging :as log]
            [clojure.string :as s]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.core.async :as a]))

(def ^:const base-url "https://api.tdameritrade.com/v1/marketdata")

(def token (env :marketdata-token))

(def- make-api-request
  "Returns the result of an HTTP request action targeting the TDA marketdata API."
  [client request-method url & [opts]]
  (let [headers {"Authorization" (format "Bearer %s" token)
                 "Content-Type" "application/json"}
        request-data {:connection-manager (:conn-mgr client)
                      :method request-method
                      :url url
                      :headers headers
                      :throw-exceptions false}
        http-response (http/request (merge request-data opts))
        response-data {:data (json/parse-string (:body http-response))}]
    (log/debug response-data)
    (response-data)))

(def get-quote
  [symbol & [options]])
