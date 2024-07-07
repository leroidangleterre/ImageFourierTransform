package imagefouriertransform;

/**
 * Representation of a complex number
 *
 * @author arthu
 */
public class Complex {

    private double realPart, imaginaryPart;

    /**
     * Create a complex number with its real and imaginary parts.
     *
     * @param newRe
     * @param newIm
     */
    public Complex(double newRe, double newIm) {
        this.realPart = newRe;
        this.imaginaryPart = newIm;
    }

    /**
     * Create a complex number of norm 1 with the specified argument.
     *
     * @param newArgument
     */
    public Complex(double newArgument) {
        this.realPart = Math.cos(newArgument);
        this.imaginaryPart = Math.sin(newArgument);
//        System.out.println("    complex: " + realPart + ", " + imaginaryPart);
    }

    /**
     * Create the complex number with value zero.
     *
     */
    public Complex() {
        this(0, 0);
    }

    /**
     * Return a new Complex number with its value scaled.
     *
     * @param scalar
     * @return a new Complex number.
     */
    public Complex multiply(double scalar) {
        return new Complex(this.realPart * scalar, this.imaginaryPart * scalar);
    }

    /**
     * Return the result of this complex number multiplied by another one.
     *
     * @param other
     * @return a new Complex number.
     */
    public Complex multiply(Complex other) {
        double a = this.realPart;
        double b = this.imaginaryPart;
        double c = other.realPart;
        double d = other.imaginaryPart;
        return new Complex(a * c - b * d, b * c + a * d);
    }

    /**
     * Return a new Complex number with its value divided.
     *
     * @param scalar
     * @return a new Complex number.
     */
    public Complex divide(double scalar) {
        if (scalar != 0) {
            return new Complex(this.realPart / scalar, this.imaginaryPart / scalar);
        } else {
            return new Complex(this.realPart, this.imaginaryPart);
        }
    }

    /**
     * Change the value of this complex number by adding another one to it
     *
     * @param addedValue
     */
    public void increment(Complex addedValue) {
        this.realPart += addedValue.realPart;
        this.imaginaryPart += addedValue.imaginaryPart;
    }

    /**
     * Get the norm of the Complex number.
     *
     */
    public double getNorm() {
        return Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
    }

    /**
     * Get the real part of this complex number
     *
     * @return the real part
     */
    public int getRealPart() {
        return (int) this.realPart;
    }

}
