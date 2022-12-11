# invitation-handler-backend

Backend realizado en Spring boot.

Database -> H2.

[RestAPI Endpoints]

{People}

Base path -> /api/people
getByInvitationId -> /invitation/{id}

{Invitations}

Base path -> /api/invitations

(Post) validate -> /validate Validar que la identificador y la password sean correctos.
(Put) registerAttendance -> /register-attendance/{invitationId} Registrar asistencia de las personas.

[Views Endpoints]

{Invitations}

Base path -> /invitations

(Get) index -> / Ver todas las invitaciones

(Get) create -> /create Crear nueva invitacion - View
(Post) onCreate -> /create Crear nueva invitacion - Funcionalidad de registro


(GET) details -> /details/{id} Obtener detalles de la invitacion
