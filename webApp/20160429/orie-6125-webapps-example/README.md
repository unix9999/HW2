# Web app

This repository contains a simple example web application that can
perform some rudimentary operations on user-provided data.

The focus of this example is to demonstrate user interaction and
testing for web applications.

## Running

Run

    python3 run_local.py

in this directory to run the web application. It will be served up at
`127.0.0.1:5000`. With the application running, we can test it by
running

    python3 -m unittest tests.py

The ``run.py`` file can be used instead to run a copy of the
application that listens to external connections; you will need to run
the command as root, and you should be careful doing this.
