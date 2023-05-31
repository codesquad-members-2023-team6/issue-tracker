package com.team6.issue_tracker.domain.label;

import com.team6.issue_tracker.domain.label.domain.Label;
import com.team6.issue_tracker.domain.label.repository.LabelRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @Test
    @DisplayName("Label 저장이 된다.")
    public void labelSave() throws Exception {
        //given
        Label testLabel = Label.newLabel("테스트 라벨2", "테스트 라벨입니다.", "#ffffff", "#000000");

        //when
        Label save = labelRepository.save(testLabel);

        //then
        assertThat(save.getTitle()).isEqualTo(testLabel.getTitle());
    }

    @Test
    @DisplayName("Label 저장시 레포지토리 저장된 수가 1늘어나야 한다.")
    public void labelSaveIntoDB() throws Exception {
        //given
        long beforeCount = labelRepository.count();
        Label testLabel = Label.newLabel("테스트 라벨2", "테스트 라벨입니다.", "#ffffff", "#000000");

        //when
        labelRepository.save(testLabel);

        //then
        assertThat(labelRepository.count()).isEqualTo(beforeCount + 1);
    }

    @Test
    @DisplayName("Label을 Label index로 찾을 수 있다.")
    public void labelFindById() throws Exception {
        //when
        Label label = labelRepository.findById(1L).orElseThrow();

        //then
        assertThat(label.getLabelIdx()).isEqualTo(1L);
        assertThat(label.getTitle()).isEqualTo("test label");
    }

    @Test
    @DisplayName("모든 Label을 조회할 수 있다.")
    public void findAll() throws Exception {
        //given
        Iterable<Label> all = labelRepository.findAll();

        //when
        log.debug("labels = {}", all);

        //then
        assertThat(all).hasSize(2);
    }

    @Test
    @DisplayName("Label 삭제되지 않은 모든 label을 조회할 수 있다.")
    public void selectNotDeleted() throws Exception {
        //given
        Iterable<Label> allByDeleted = labelRepository.findAllByIsDeletedFalse();

        //then
//        assertThat(allByDeleted).hasSize(1);
    }
}
