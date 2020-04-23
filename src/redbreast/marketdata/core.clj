(ns redbreast.marketdata.core
  (:require [clojure.tools.logging :as log]
            [clojure.string :as s]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.core.async :as a]))

(def base-url "https://api.tdameritrade.com/v1/marketdata")

(def token (env :marketdata-token))

(def get-quote
  [symbol & [options]])
