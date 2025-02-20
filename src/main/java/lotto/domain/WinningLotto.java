package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningNumbers, final LottoNumber bonusNumber) {
        validateBonusNumberDuplicated(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningStatistics calculateStatistics(final List<Lotto> lottos) {
        Map<Rank, Integer> statistics = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (final Lotto lotto : lottos) {
            Rank rank = Rank.checkRank(lotto.calculateMatchCount(winningNumbers), lotto.contains(bonusNumber));
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return new WinningStatistics(statistics);
    }

    private void validateBonusNumberDuplicated(final Lotto winningNumbers, final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }
}
