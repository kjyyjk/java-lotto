package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.mock.TestLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    private final LottoGenerator lottoGenerator = new TestLottoGenerator();

    @DisplayName("특정 번호를 포함하는 지 여부를 반환할 수 있다")
    @CsvSource(value = {"1:true", "6:true", "8:false"}, delimiterString = ":")
    @ParameterizedTest
    void 특정_번호를_포함하는_지_여부를_반환할_수_있다(int number, boolean expected) {
        //given
        Lotto lotto = lottoGenerator.generate();

        //when
        boolean result = lotto.contains(new LottoNumber(number));

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("6개의 고유한 번호가 아니라면 예외를 던진다")
    @MethodSource("returnWrongSizeNumbers")
    @ParameterizedTest
    void _6개의_고유한_번호가_아니라면_예외를_던진다(List<LottoNumber> numbers) {
        //then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 고유한 번호를 입력해야 합니다.");
    }

    @DisplayName("일치하는_번호의_개수를_계산하여_반환할_수_있다")
    @Test
    void 일치하는_번호의_개수를_계산하여_반환할_수_있다() {
        //given
        Lotto lotto = lottoGenerator.generate();
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        //when
        int result = lotto.calculateMatchCount(winningLotto);

        //then
        assertThat(result).isEqualTo(5);
    }

    static Stream<Arguments> returnWrongSizeNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5)
                        .stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7)
                        .stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())),
                Arguments.arguments(List.of(1, 2, 3, 4, 6, 6)
                        .stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()))
        );
    }
}
