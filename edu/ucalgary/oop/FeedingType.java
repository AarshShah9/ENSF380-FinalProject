package edu.ucalgary.oop;

/**
 * The FeedingType enum represents a set of predefined feeding types,
 * specifically
 * indicating the time of day that the animals should be fed.
 * This enumeration is intended to be used in the context of the project
 * for modeling and categorizing different feeding schedules within the system.
 *
 * The enum currently supports the following feeding types:
 * - CREPUSCULAR: Represents animals that are active during twilight hours
 * - NOCTURNAL: Represents animals that are active during the night
 * - DIURNAL: Represents animals that are active during the day
 */
public enum FeedingType {
    // Enumerated values representing different feeding types
    CREPUSCULAR {
        /**
         * Returns the feed start times for crepuscular animals.
         *
         * @return an ArrayList of Integer values representing the start hours (in
         *         24-hour format) for feeding
         */
        public int getFeedStartTime() {
            return 19;

        }

    },
    NOCTURNAL {
        /**
         * Returns the feed start times for nocturnal animals.
         *
         * @return an ArrayList of Integer values representing the start hours (in
         *         24-hour format) for feeding
         */
        public int getFeedStartTime() {
            return 0;

        }

    },
    DIURNAL {
        /**
         * Returns the feed start times for diurnal animals.
         *
         * @return an ArrayList of Integer values representing the start hours (in
         *         24-hour format) for feeding
         */
        public int getFeedStartTime() {
            return 8;
        }

    };

    /**
     * Abstract method that must be implemented by each feeding type to provide
     * the specific feeding start times.
     *
     * @return an ArrayList of Integer values representing the start hours (in
     *         24-hour format) for feeding
     */
    public abstract int getFeedStartTime();
}
