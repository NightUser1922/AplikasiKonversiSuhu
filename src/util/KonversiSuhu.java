package util;

public class KonversiSuhu {
    public static double keCelsius(double nilai, String asal) {
        switch (asal) {
            case "Fahrenheit": return (nilai - 32) * 5 / 9;
            case "Reamur": return nilai * 5 / 4;
            case "Kelvin": return nilai - 273.15;
            default: return nilai;
        }
    }

    public static double keFahrenheit(double nilai, String asal) {
        switch (asal) {
            case "Celsius": return (nilai * 9 / 5) + 32;
            case "Reamur": return (nilai * 9 / 4) + 32;
            case "Kelvin": return (nilai - 273.15) * 9 / 5 + 32;
            default: return nilai;
        }
    }

    public static double keReamur(double nilai, String asal) {
        switch (asal) {
            case "Celsius": return nilai * 4 / 5;
            case "Fahrenheit": return (nilai - 32) * 4 / 9;
            case "Kelvin": return (nilai - 273.15) * 4 / 5;
            default: return nilai;
        }
    }

    public static double keKelvin(double nilai, String asal) {
        switch (asal) {
            case "Celsius": return nilai + 273.15;
            case "Fahrenheit": return (nilai - 32) * 5 / 9 + 273.15;
            case "Reamur": return nilai * 5 / 4 + 273.15;
            default: return nilai;
        }
    }
}
