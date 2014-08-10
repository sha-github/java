<html>
	<head>
		<script src='https://swww.tokbox.com/webrtc/v2.0/js/TB.min.js'></script>
	</head>
	<body>
	Happy Chatting!! 
		 <div id="myPublisherDiv"></div>
        <script type="text/javascript">
          // Initialize API key, session, and token...
          // Think of a session as a room, and a token as the key to get in to the room
          // Sessions and tokens are generated on your server and passed down to the client
          var apiKey = "${apiKey}";
          var sessionId = "${sessionId}";
          var token = "${token}";
          TB.setLogLevel(TB.DEBUG);
          // Initialize session, set up event listeners, and connect
          var session = TB.initSession(sessionId);
          session.addEventListener('sessionConnected', sessionConnectedHandler);
		  session.addEventListener("streamCreated", streamCreatedHandler);
          session.connect(apiKey, token);
          
          function sessionConnectedHandler(event) {
			subscribeToStreams(event.streams);
            var publisher = TB.initPublisher(apiKey, 'myPublisherDiv');
            session.publish(publisher);
          }
		  
		  function streamCreatedHandler(event) {
				subscribeToStreams(event.streams);
		}
		  
		  function subscribeToStreams(streams) {
				for (var i = 0; i < streams.length; i++) {
					var stream = streams[i];
					if (stream.connection.connectionId != session.connection.connectionId) {
						var div = document.createElement("div");
						div.setAttribute("id",stream.streamId);
						document.body.appendChild(div);
						session.subscribe(stream, div.id);
					}
				}
			}
		  
		  
        </script>
	<body>
</html>