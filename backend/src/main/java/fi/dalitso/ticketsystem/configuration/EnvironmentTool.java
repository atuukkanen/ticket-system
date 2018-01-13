package fi.dalitso.ticketsystem.configuration;

public class EnvironmentTool {

    /**
     * Fetches the given environmental variable.
     * @param name The name of the wanted environmental variable.
     * @throws RuntimeException if the environmental variable is not available.
     * @return The value of the environmental variable.
     */
    public static String getEnvironmentVariable(String name) {
        String envVariable = System.getenv(name);
        if (envVariable == null || envVariable.isEmpty())
            throw new RuntimeException("Required environmental variable "
                    + name + " is not set and non-empty.");
        return envVariable;
    }

    /**
     * Fetches the given environmental variable if available, otherwise returns
     * the default value.
     * @param name The name of the wanted environmental variable.
     * @param defaultReturn Default value to be returned if the variable is not
     *                      available.
     * @return The environmental variable's value, or the given default value.
     */
    public static String getEnvironmentVariable(String name, String defaultReturn) {
        try {
            return getEnvironmentVariable(name);
        } catch (RuntimeException ignored) {}
        return defaultReturn;
    }
}
