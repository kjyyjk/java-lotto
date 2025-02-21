package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.VALUES);
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.subList(0, LOTTO_SIZE));
    }
}
