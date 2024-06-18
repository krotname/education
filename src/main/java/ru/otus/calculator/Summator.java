package ru.otus.calculator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Summator {
    private final List<Data> listValues = new ArrayList<>();
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;

    public void calc(Data data) {

        listValues.add(data);
        if (listValues.size() % 60_000_000 == 0) {
            listValues.clear();
        }
        reCalc(data);
    }

    private void reCalc(Data data) {
        sum += data.value();
        sumLastThreeValues = data.value() + prevValue + prevPrevValue;
        prevPrevValue = prevValue;
        prevValue = data.value();
        someValue += (sumLastThreeValues * sumLastThreeValues / (data.value() + 1) - sum);
        someValue = Math.abs(someValue) + listValues.size();
        someValue += (sumLastThreeValues * sumLastThreeValues / (data.value() + 1) - sum);
        someValue = Math.abs(someValue) + listValues.size();
        someValue += (sumLastThreeValues * sumLastThreeValues / (data.value() + 1) - sum);
        someValue = Math.abs(someValue) + listValues.size();
    }
}
