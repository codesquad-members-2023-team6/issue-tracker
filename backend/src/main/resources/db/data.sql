# member init
INSERT `issue_tracker_schema`.member (member_idx, id, name, password, profile_image_url, created_at)
VALUES (1, 'realsnoopso', '스눕소', '1234', 'https://ca.slack-edge.com/T74H5245A-U04FHDY4DFV-1a828514d33d-512', now()),
       (2, 'sarang_daddy', '사랑대디', '1234', 'https://avatars.githubusercontent.com/u/109648042?v=4', now()),
       (3, 'lvalentine6', '로이' , '1234', 'https://avatars.githubusercontent.com/u/77956808?v=4', now()),
       (4, 'new-pow', '1234', '이린', 'https://avatars.githubusercontent.com/u/103120173?v=4', now());

# issue init
INSERT `issue` (issue_idx, title, contents, is_open, created_at, is_deleted, writer, assignee)
VALUES (1, '새 기능 추가', '테스트 이슈입니다.', 1, now(), 0, 1, 2),
       (2, '버그 수정', '테스트 이슈입니다.2', 1, now(), 0, 3, 3),
       (3, 'UI 개선', '테스트 이슈입니다.', 1, now(), 0, 3, 4),
       (4, '새로운 사용자 지정 필드 추가', '테스트 이슈입니다.', 1, now(), 0, 1, null),
       (5, '새로운 사용자 지정 필드 추가', '테스트 이슈입니다.', 1, now(), 0, 1, null),
       (6, '검색 기능 개선', '테스트 이슈입니다.', 1, now(), 0, 3, null),
       (7, '새로운 보고서 템플릿 추가', '테스트 이슈입니다.', 1, now(), 0, 2, 3),
       (8, '새로운 기능에 대한 사용자 테스트 추가', '테스트 이슈입니다.', 1, now(), 0, 3, 1),
       (9, '새로운 기능에 대한 성능 테스트 추가', '테스트 이슈입니다.', 1, now(), 0, 2, null);

# comment init
INSERT comment (contents, created_at, edited_at, is_deleted, issue_idx, created_by)
VALUES ('새 기능 추가에 대한 의견입니다.', now(), null, 0, 1, 2),
       ('새 기능 추가에 대한 또 다른 의견입니다.', now(), null, 0, 1, 1),
       ('버그 수정에 대한 의견입니다.', now(), null, 0, 2, 3),
       ('버그 수정은 언제 완료되나요?', now(), null, 0, 2, 2),
       ('UI 개선에 대한 의견입니다.', now(), null, 0, 3, 4),
       ('UI의 어떤 부분을 개선하나요.', now(), null, 0, 3, 1),
       ('새로운 사용자 지정 필드 추가에 대한 의견입니다.', now(), null, 0, 4, 2),
       ('새로운 사용자 지정 필드 추가에 대한 의견입니다.', now(), null, 0, 5, 1),
       ('새로운 의견입니다.', now(), null, 0, 4, 4),
       ('새로운 의견입니다.', now(), null, 0, 5, 2),
       ('검색 기능 개선에 대한 의견입니다.', now(), null, 0, 6, 2),
       ('코드스쿼드 짱.', now(), null, 0, 6, 3),
       ('새로운 보고서 템플릿 추가에 대한 의견입니다.', now(), null, 0, 7, 4),
       ('빨리 하세요.', now(), null, 0, 7, 3),
       ('좋아요.', now(), null, 0, 7, 1),
       ('새로운 기능에 대한 사용자 테스트에 대한 의견입니다.', now(), null, 0, 8, 3),
       ('새로운 기능에 대한 성능 테스트 추가', now(), null, 0, 9, 1),
       ('성능 보고서 첨부하세요.', now(), null, 0, 9, 2),
       ('첨부 자료 함께 확인하세요.', now(), null, 0, 9, 4);

# labbel init
INSERT label (label_idx, title, description, background_color, style, is_deleted)
VALUES (1, 'UI', '이것은 레이블입니다.', '#2ecc71', 'outline', false),
       (2, 'API', '이것은 레이블2입니다.', '#2ecc71', 'solid', false),
       (3, '작소 화이팅', '이것은 레이블3입니다.', '#3498db', 'solid', false),
       (4, '로눅스를 아십니까?', '이것은 레이블4입니다.', '#f39c12', 'solid', false);

# milestone init
INSERT milestone (title, is_open, ended_at, contents, is_deleted)
VALUES ('마일스톤1', true, '2023-12-31', 'Sample contents 1', false),
       ('마일스톤2', true, '2023-07-22', 'Sample contents 2', false),
       ('마일스톤3', true, '2023-09-25', 'Sample contents 3', false);
