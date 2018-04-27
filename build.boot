(set-env!
  :source-paths #{"src/cljs"}
  :resource-paths #{"html"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-2"]
                  [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]
                  ;; [org.clojure/tools.nrepl "0.2.12"]
                  [com.cemerick/piggieback "LATEST" :scope "test" :exclusions [org.clojure/clojure]]
                  [weasel                  "0.7.0"  :scope "test" :exclusions [org.clojure/clojure]]
                  [org.clojure/tools.nrepl "LATEST" :scope "test" :exclusions [org.clojure/clojure]]
                  [org.clojure/clojurescript "LATEST"]
                  [adzerk/boot-reload "LATEST"]
                  [adzerk/boot-cljs-repl "LATEST"]
                  ])

(require '[adzerk.boot-cljs :refer [cljs]]
  '[pandeiro.boot-http :refer [serve]]
  '[adzerk.boot-reload :refer [reload]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  )


(deftask dev
  "Launch development environment. Oh yeah!"
  []
  (comp
    (serve :dir "target")
    (watch)
    (reload)
    (cljs-repl)
    (cljs)
    (target :dir #{"target"})))

(deftask client
  "Connect a client repl to the build process"
  []
  (comp
    (repl :client true :eval '(start-repl))))
