# redbreast

Telegram bot for Stonks.

## Usage

    $ lein uberjar
    $ java -cp target/uberjar/redbreast.jar clojure.main -m redbreast.core

## Deployment

This project is configured to automatically deploy all changes merged
into master to Heroku. If there's an issue and you need to stop, start
or restart the process in Heroku you can follow the simple commands.

    $ heroku ps:kill worker
    $ heroku ps:start worker
    $ heroku ps:restart worker

During runtime the process requires API keys to be set in the environment
in order to access the [Telegram][0] and [market data][4] APIs. You can use
the same Heroku command to set/configure those variables.

    $ heroku config:set TELEGRAM_TOKEN=foo
    $ heroku config:set MARKETDATA_TOKEN=bar

## Libraries

- [morse][0]
- [clj-http][1]
- [cheshire][2]
- [clj-xchart][3]

[0]: https://github.com/Otann/morse
[1]: https://github.com/dakrone/clj-http
[2]: https://github.com/dakrone/cheshire
[3]: https://github.com/hyPiRion/clj-xchart
