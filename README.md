# Padelante

# FASE 0

|**Nombre y Apellidos**|**Correo**|**Usuario Github**|
|:-----------------:|:-----:|:-------------:|
|Rubén Catalán Medina|r.catalanmedina.2018@alumnos.urjc.es|Ruben-urjc|
|Álvaro González Aroas | a.gonzalezar.2018@alumnos.urjc.es|alvaroga2175|
|Daniel Haro Murcia|d.haro.2018@alumnos.urjc.es|DaniURJC|
|Silvia Ventura Cabreja|s.ventura.2018@alumnos.urjc.es|Silvia-vcp|

## Entidades
- **Usuario**. Los usuarios son aquellos tanto registrados como no, que puede hacer uso de la web para formar sus campeonatos de padel, formando pareja con otro usuario.
  - Anónimo
  - Registrado
  - Administrador
- **Pareja**. Unión de dos usuarios registrados.
- **Torneo**. Conjunto de partidos.
- **Partido**. Jugado por 2 Parejas.

## Permisos usuarios
- **Usuario anónimo:** Solo podrá mirar los torneo públicos, información general de la web, reglas de los torneos y registrarse
- **Usuario registrado:** Podrá crear torneos privados, compartirlos con otros jugadores por email (¿o generar código o enlace?), modificar características del torneo que crea, modificar su perfil, ver estadísticas, ver histórico de partidos y parejas, unirse a torneos, añadir resultado a sus partidos, confirmar resultado.
- **Usuario administrador:** Expulsar gente de la aplicación, crear y borrar torneos públicos.

## Imágenes
- **Usuario registrado:** Imagen de perfil. Opcional. Modificable
- **Torneo: Imagen de torneo de fondo:** Defecto. Modificable
- **Pareja: Imagen de pareja:** Opcional. Modificable

## Gráficos
- **Porcentaje de victoria total:** Cúmulo individual. Gráficos de sectores
- **Evolución individual de puntos de karma:** Gráfico lineal

## Tecnología complementaria
- **Enviar email:** Enviar PDFs a los ganadores de torneos.
- **Generar PDF:** Para campeón de torneo. Para descargar estadísticas.

## Algoritmo o consulta avanzada
- **Aleatoriedad de emparejamientos inicial.**
- **Cálculo automático de próximos partidos en un torneo.**
- **Clasificación en competiciones de formato liga.**
- **Karma:** Puntos totales que bajen o suban en función del karma de la pareja contraria.

# FASE 1

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Login.png" align="center"  width="300"></img>
<p>Un usuario puede hacer Login o Registrarse</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Resgistrate.png" width="300"></img>
<p>Al registrarse, introducirá su nombre de usuario, nombre completo, email, contraseña y aceptará los términos y condiciones</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Main1.png" width="300"></img>
<p>Desde la página inicial, cualquier usuario podrá ver los torneos públicos, pero solo los registrados podrán crear torneos o iniciar sesión</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Main3.png" width="300"></img>
<p>En la página inicial se mostrarán inicialmente 6 torneos públicos. Para ver más, habrá que pulsar en "cargar más"</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminPage.png" width="300"></img>
<p>Un administrador podrá buscar tanto torneos como usuarios para poder gestionar la aplicación (resolver conflictos, eliminar)</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ErrorPage.png" width="300"></img>
<p>La página de error se mostrará cuando haya fallos de conexión con el servidor</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/MensageNotification.png" width="300"></img>
<p>Un usuario registrado podrá ver notificaciones y mensages referentes a los torneos en los que está apuntado</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Information.png" width="300"></img>
<p>Cualquier jugador que haya jugado un partido podrá añadir el resultado</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Notifications.png" width="300"></img>
<p>Los usuarios registrados podrán ver notificaciones</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserOpctions.png" width="300"></img>
<p>Los usuarios registrados podrán ver su perfil y cerrar sesión</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ProfileDates.png" width="300"></img>
<p>Dentro del perfil se podrán ver los datos generales del usuario registrado</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/EditProfile.png" width="300"></img>

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/EditProfile2.png" width="300"></img>
<p>A su vez, se podrá editar</p>

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Adjust.png" width="300"></img>
<p>También se podrá seleccionar qué tipo de notificaciones por correo o en la propia página</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ChangePassword.png" width="300"></img>
<p>Se podrá cambiar la contraseña</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/MyTournaments.png" width="300"></img>
<p>Por último, dentro del perfil se pueden ver los torneos en los que el usuario ha jugado o está jugando</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/TournamentTemplate.png" width="300"></img>
<p>En la página del torneo se podrán ver los participantes</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Add.png" width="300"></img>
<p>En la página del torneo, un usuario que haya sido invitado podrá invitar a su pareja</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Bracket.png" width="300"></img>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Bracket2.png" width="300"></img>
<p>Se podrán ir viendo los cruces de los partidos</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminAdjust.png" width="300"></img>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminAdjust2.png" width="300"></img>
<p>El usuario creador del torneo podrá cambiar diferentes ajustes relativos al mismo</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/CreateTournament1.png" width="300"></img>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/CreateTournamente2.png" width="300"></img>
<p>Un usuario registrado podrá crear torneos rellenado un formulario</p>

**Navegation Photos**

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Navegation 1.png" width="1000"></img>

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Navegation 2.png" width="1000"></img>


<!-- ESTO SERIA USANDO MARKDOWN PERO NO HE PODIDO HACER LAS IMAGENES MAS PEQUEÑAS, POR ESO HE USADO HTML

![Login](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Login.png)
![Registrate](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Resgistrate.png)
![Main1](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Main1.png)
![Main3](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Main3.png)
![AdminPage](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminPage.png)
![ErrorPage](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ErrorPage.png)
![MesangeNotification](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/MensageNotification.png)
![Notification](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Notifications.png)
![UserOpctions](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserOpctions.png)
![PRofileDAtes](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ProfileDates.png)
![EditProfile](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/EditProfile.png)
![EditProfile2](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/EditProfile2.png)
![Adjust](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Adjust.png)
![ChangePassword](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ChangePassword.png)
![MyTournaments](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/MyTournaments.png)
![TournamentsTemplate](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/TournamentTemplate.png)
![Bracket](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Bracket.png)
![Bracket2](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Bracket2.png)
![AdminAdjust](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminAdjust.png)
![AdminAdjust2](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/AdminAdjust2.png)
![CreateTournament1](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/CreateTournament1.png)
![CreateTournament2](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/CreateTournamente2.png)
![Navegation 2](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Navegation 2.png)
![Navegation 1](https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Navegation 1.png)-->



