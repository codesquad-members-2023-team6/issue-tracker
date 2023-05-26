# member init
INSERT `issue_tracker_schema`.member (member_idx, id, password, profile_image_url, github_join)
VALUES (1, 'iirin', '1234', '', true),
       (2, 'roy', '1234', '', false),
       (3, 'snoop', '1234', '', true),
       (4, 'sarangDaddy', '1234', '', false);

# issue init
INSERT `issue` (title, contents, is_open, created_at, is_deleted, writer, assignee)
VALUES ('test issue1', '테스트 이슈입니다.', 0, now(), 0, 1, 2),
        ('test issue2', '테스트 이슈입니다.', 1, now(), 1, 2, 2),
        ('test issue3', '테스트 이슈입니다.', 0, now(), 1, 2, 2),
        ('test issue4', '테스트 이슈입니다.', 1, now(), 0, 2, 2);

# comment init
INSERT comment (comment_idx, contents, created_at, edited_at, is_open, issue_idx, created_by)
VALUES (1, 'test comment', now(), null, 1, 1, 2);

# labbel init
INSERT label (label_idx, title, description, background_color, text_color, is_deleted)
VALUES (1, 'test label', '테스트 라벨입니다.', '#000000', '#ffffff', false),
        (2, 'deleted label', '삭제된 라벨입니다.', '#000000', '#Fffffff', true);

# milestone init
INSERT milestone (title, is_open, ended_at, contents, is_deleted)
VALUES ('test milestone', true, '2023-12-31', '내용입니다.', false);
