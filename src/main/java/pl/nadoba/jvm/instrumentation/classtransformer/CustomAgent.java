package pl.nadoba.jvm.instrumentation.classtransformer;

import java.lang.instrument.Instrumentation;

public class CustomAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new JavassistClassTransformer());
    }
}
