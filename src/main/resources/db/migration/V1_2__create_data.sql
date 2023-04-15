INSERT INTO public."role"(name, create_at)
VALUES ('ADMIN', '2023-04-14T16:20:02.320253');

INSERT INTO public."permission"(name, create_at)
VALUES ('READ', '2023-04-14T16:20:02.320253');

INSERT INTO public."permission"(name, create_at)
VALUES ('WRITE', '2023-04-14T16:20:02.320253');

INSERT INTO public."roles_permissions"(role_id, permission_id)
VALUES (1, 1);

INSERT INTO public."roles_permissions"(role_id, permission_id)
VALUES (1, 2);

INSERT INTO public."user"(user_name, password, email, create_at, listener_id, role_id)
VALUES ('admin', 'YWRtaW4=', 'artemkulakovich237@gmail.com', '2023-04-14T16:20:02.320253', 0, 1);