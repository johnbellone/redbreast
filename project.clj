(defproject redbreast "0.2.0-SNAPSHOT"
  :description "A simple Telegram bot written in Clojure."
  :url "https://github.com/johnbellone/redbreast"

  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"
            :year 2020
            :key "apache-2.0"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-logging-config "1.9.12"]
                 [environ "1.2.0"]
                 [morse "0.5.0-SNAPSHOT"]
                 [cheshire "5.11.0"]
                 [compojure "1.7.0"]
                 [info.sunng/ring-jetty9-adapter "0.22.0"]]
  :min-lein-version "2.6.1"
  :uberjar-name "redbreast.jar"
  :target-path "target/%s"
  :repl-options {:init-ns user}
  :jvm-opts ["-server"]
  :profiles {:dev [:project/dev :profiles/dev]
             :uberjar {:omit-source true
                       :aot :all
                       :plugins [[lein-environ "1.2.0"]]
                       :source-paths ["target/prod"]}})
