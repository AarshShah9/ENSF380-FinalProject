package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;

public enum FeedingType {
    CREPUSCULAR {
        public ArrayList<Integer> getFeedStartTimes() {
            return new ArrayList<Integer>(Arrays.asList(19));

        }

    },
    NOCTURNAL {
        public ArrayList<Integer> getFeedStartTimes() {
            return new ArrayList<Integer>(Arrays.asList(12, 1, 2));

        }

    },
    DIURNAL {
        public ArrayList<Integer> getFeedStartTimes() {
            return new ArrayList<Integer>(Arrays.asList(8));
        }

    };

    public abstract ArrayList<Integer> getFeedStartTimes();
}
