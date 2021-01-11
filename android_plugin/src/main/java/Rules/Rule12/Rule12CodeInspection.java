package Rules.Rule12;

import Rules.BaseRuleCodeInspection;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;

public class Rule12CodeInspection extends BaseRuleCodeInspection {

    final String methodToDetect = "getSharedPreferences";
    final String methodArgsToDetect = "MODE_PRIVATE";

    Rule12CodeInspection() {
        super("Use SharedPreferences in private mode",
                "Rule12_SharedPreferences_Private_Mode",
                "Shared preference(s) saving (are) not private",
                ProblemHighlightType.GENERIC_ERROR);
    }

    @NotNull
    @Override
    public Rule12InspectionVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new Rule12InspectionVisitor(holder, methodToDetect, methodArgsToDetect, errorMessage, type);
    }

}