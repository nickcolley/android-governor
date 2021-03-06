Governor
========

Like AirDroid, but with freedomsauce.

* * *

Governor is an attempt at providing Android users with a web-based portal to
interact with their phones. At the moment, it's in a "seedling" stage and is
starting to provide a base framework for working with HTTP requests. It's not
yet capable of any meaningful device management.

Building
--------

Governor consists of two distinct applications: the HTTP server running on the
device, which also hosts the dynamic controllers responsible for generating
response data, and the web user interface which the server delivers to
browsers.

The application can be built easily -- just run the Gradle wrapper script:

    $ ./gradlew

The web interface is easy to build independently of the overall application --
the dependencies can be fetched like so:

    $ cd app-web
    $ npm install -g bower gulp # you may need sudo here
    $ npm install
    $ bower install

And subsequent builds are just a call to Gulp away:

    $ cd app-web
    $ gulp

You can also watch the web UI for changes, optionally using LiveReload to save
time when tweaking the UI:

    $ cd app-web
    $ gulp watch --live-reload

To further speed up frontend development, the UI can be tested on a server
independently of the device hosting the Governor controllers. To achieve this,
you need to configure a filter on all jQuery AJAX requests like so:

    $.ajaxPrefilter(function(options, originalOptions, jqXhr) {
        options.url = 'http://192.168.1.138:8080' + options.url;
    });

Pop the above into ```app-web/script/lib/local.js```, save and allow gulp to
rebuild all the assets.

To do
-----

This is an ambitious list, and there's a chance that some of these actions
will depend on the device being rooted. It'd be awesome if we could include all
of this functionality, though:

* HTTP server
    * Move HTTP server out of activity and into a service
    * Display a notification when the server is running
* Device management
    * Camera/screenshotting
        * Take photo from front and back cameras
        * Take a screenshot/screencast
    * Clipboard
        * Insert text into clipboard
        * Export contents from clipboard
    * File management
        * Browse, move and copy files
        * Create and modify text files
        * Sample media files
        * View storage information
    * Geolocation
        * Dude, where's my phone?
    * Messaging
        * Read SMS
        * Send SMS
    * Package management
        * Install APKs
        * Remove existing apps
    * Phone calls
        * Initiate phone calls
        * Reject/dismiss incoming phone calls with a message
    * System management
        * View resource usage

Thanks
------

Governor would not be possible were it not for the following excellent open
source projects:

* [Google Gson](https://code.google.com/p/google-gson/) - a library for converting Java Objects into
  their JSON representation
* [NanoHttpd](https://github.com/NanoHttpd/nanohttpd) - the tiny, embeddable Java HTTP server
