(defproject redbreast "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/johnbellone/redbreast"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clj-logging-config "1.9.12"]
                 [environ "1.1.0"]
                 [org.danielsz/system "0.4.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [morse "0.4.3"]]

  :plugins [[lein-environ "1.1.0"]]

  :min-lein-version "2.6.1"

  :uberjar-name "redbreast.jar"

  :main ^:skip-aot redbreast.core
  :target-path "target/%s"

  :repl-options {:init-ns user}

  :profiles {:dev
             {:dependencies [[figwheel "0.5.18"]
                             [figwheel-sidecar "0.5.18"]
                             [cider/piggieback "0.4.0"]
                             [cider/cider-nrepl "0.18.0"]
                             [lein-doo "0.1.11"]
                             [reloaded.repl "0.2.4"]]

              :plugins [[lein-figwheel "0.5.18"]
                        [lein-doo "0.1.11"]]

              :source-paths ["dev"]
              :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}

             :uberjar
             {:source-paths ^:replace ["src/clj" "src/cljc"]
              :prep-tasks ["compile"
                           ["cljsbuild" "once" "min"]]
              :hooks []
              :omit-source true
              :aot :all}})
