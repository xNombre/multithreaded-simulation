package simulation.simulation;

public class RandomAccidentFactory implements AccidentFactory{

    @Override
    public Accident getAccident() {
        var randomAccidentType = AccidentType.getRandomAccident();
        return new Accident(randomAccidentType, AccidentIcon.getAssociatedAccidentIcon(randomAccidentType));
    }
}
