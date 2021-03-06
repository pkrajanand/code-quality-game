package com.thepracticaldeveloper.devgame.modules.badges.calculators;

import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.util.IssueDateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Component
public class EarlyBirdBadgeCalculator implements BadgeCalculator {
    private static final int EXTRA_POINTS = 100;

    @Value("${earlyBirdDate}")
    private String earlyBirdDate;

    @Override
    public Optional<SonarBadge> badgeFromIssueList(Set<Issue> issues) {
        if (issues.stream().filter(i -> i.getCloseDate() != null)
                .anyMatch(i -> IssueDateFormatter.format(i.getCloseDate()).isBefore(LocalDate.parse(earlyBirdDate)))) {
            return Optional.of(new SonarBadge("Early Bird", "Resolve any issue before " + earlyBirdDate, EXTRA_POINTS));
        } else {
            return Optional.empty();
        }
    }

}
