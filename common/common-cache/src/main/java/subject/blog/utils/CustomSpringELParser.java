package subject.blog.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class CustomSpringELParser {

    public static Object getDynamicValue(String[] parameterNames, Object[] args, String[] keys) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        List<String> collect = Arrays.stream(keys)
            .map(s -> parser.parseExpression(s).getValue(context, String.class))
            .collect(
                Collectors.toList());
        return String.join("_", collect);
    }
}
