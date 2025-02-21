package lotto.mock;

import static lotto.domain.Lotto.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;

public class TestLottoGenerator implements LottoGenerator {
    /**
     * shuffle 없이 1, 2, 3, 4, 5, 6을 번호로 가지는 로또를 생성하고 반환한다.
     */
    @Override
    public Lotto generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.VALUES);
        return new Lotto(lottoNumbers.subList(0, LOTTO_SIZE));
    }
}
