package Rules.Rule12;

import Rules.BaseRuleElementVisitor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

/**
 * Rule12CodeInspection code visitor
 */
public class Rule12InspectionVisitor extends BaseRuleElementVisitor {

    public Rule12InspectionVisitor(@NotNull ProblemsHolder holder, String methodToDetect, String methodArgsToDetect, String errorMessage, ProblemHighlightType type) {
        super(holder, methodToDetect, methodArgsToDetect, errorMessage, type);
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression expression) {
        if (Objects.equals(expression.getMethodExpression().getReferenceName(), methodToDetect)) {
            PsiExpression[] psiExpressions = expression.getArgumentList().getExpressions();

            boolean argToDetectIsPresent = false;

            for (PsiExpression psiExpression : psiExpressions) {
                //Don't evaluate first argument
                if (psiExpression != Arrays.stream(psiExpressions).findFirst().get()) {
                    if (psiExpression.getText().contains(methodArgsToDetect) || psiExpression.getText().contains("0")) {
                        argToDetectIsPresent = true;
                        break;
                    }
                }
            }
            if (!argToDetectIsPresent) {
                this.registerProblem(psiExpressions[1]);
            }
        }
        super.visitMethodCallExpression(expression);
    }

}