(defproject thing "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [korma "0.4.3"]
                 [ragtime "0.6.3"] ;; db migrations
                 [garden "1.3.2"]
                 [mysql/mysql-connector-java "6.0.5"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.9.7"]
            [ragtime/ragtime.lein "0.3.6"]]
  :ring {:handler thing.handler/app
         :nrepl {:start? true
                 :port 9998}}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
