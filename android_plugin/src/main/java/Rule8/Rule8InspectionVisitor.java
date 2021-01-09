package Rule8;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Rule8InspectionVisitor extends JavaElementVisitor {

    ProblemsHolder problemsHolder;

    final String methodToDetect = "openFileOutput";
    final String methodArgsToDetect = "MODE_PRIVATE";

    public Rule8InspectionVisitor(@NotNull ProblemsHolder holder) {
        problemsHolder = holder;
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression expression) {
        if(Objects.equals(expression.getMethodExpression().getReferenceName(), methodToDetect))
        {
            PsiExpression[] psiExpressions = expression.getArgumentList().getExpressions();
            boolean argToDetectIsPresent = false;
            for (PsiExpression psiExpression: psiExpressions) {
                if (psiExpression.getText().contains(methodArgsToDetect) || psiExpression.getText().contains("0")) {
                    argToDetectIsPresent = true;
                    break;
                }
            }
            if(!argToDetectIsPresent)
            {
                problemsHolder.registerProblem(expression, "Call(s) to internal storage (are) not private");
            }
        }
        super.visitMethodCallExpression(expression);

        //

    }
}