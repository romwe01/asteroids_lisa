Hallihallo!

Also meiner Meinung nach kann ma mal Enumeration, GameEvent & -Listener, RenderLayer, Time, UpdateManager, Updatable und mein altes Entity löschen. 
Manche würden mit Entity zusammenhängen wenn i des weiter ausgebaut hätt. Time wäre mit was anderem in Verbindung gewesen, was i a net implementiert hab
weils net nötig war, kann also a weg. GameEvent wäre mit dem gleichen wie Time in verbindung gestanden, kann also a weg.

GameAction linked ein sg. GameAction an eine Taste auf der Tastatur, also z.B. "gehe links" zu KeyEvent.VK_LEFT
hierfür gibts im inputManager die Funktion maptoKey(). sollte mit core.inputManager.mapToKey aufgerufen werden können
dies kann beim PlayerEntity eingebaut werden, wenn der das interface EventListener implementiert (im setUp())
Für das move des Players kann dann z.B. mit if (left.isPressed()) {xxx } ein Winkel mitgegeben werden.

Das wärs mal von meiner Seite :)