Hallihallo!

Also meiner Meinung nach kann ma mal Enumeration, GameEvent & -Listener, RenderLayer, Time, UpdateManager, Updatable und mein altes Entity l�schen. 
Manche w�rden mit Entity zusammenh�ngen wenn i des weiter ausgebaut h�tt. Time w�re mit was anderem in Verbindung gewesen, was i a net implementiert hab
weils net n�tig war, kann also a weg. GameEvent w�re mit dem gleichen wie Time in verbindung gestanden, kann also a weg.

GameAction linked ein sg. GameAction an eine Taste auf der Tastatur, also z.B. "gehe links" zu KeyEvent.VK_LEFT
hierf�r gibts im inputManager die Funktion maptoKey(). sollte mit core.inputManager.mapToKey aufgerufen werden k�nnen
dies kann beim PlayerEntity eingebaut werden, wenn der das interface EventListener implementiert (im setUp())
F�r das move des Players kann dann z.B. mit if (left.isPressed()) {xxx } ein Winkel mitgegeben werden.

Das w�rs mal von meiner Seite :)