package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums;

public enum EWorkOrderType {

        // Maintenance
        OIL_CHANGE("Engine oil change"),
        FILTER_REPLACEMENT("Vehicle filter replacement"),
        TUNE_UP("General engine tune-up"),
        PREVENTIVE_MAINTENANCE("Scheduled preventive maintenance"),

        // Engine
        ENGINE_DIAGNOSIS("Engine fault diagnosis"),
        ENGINE_REPAIR("Engine repair service"),
        ENGINE_REBUILD("Complete engine rebuild"),
        TIMING_BELT_REPLACEMENT("Timing belt replacement"),
        HEAD_GASKET_REPLACEMENT("Cylinder head gasket replacement"),

        // Brakes
        BRAKE_INSPECTION("Brake system inspection"),
        BRAKE_PAD_REPLACEMENT("Brake pad replacement"),
        BRAKE_DISC_REPLACEMENT("Brake disc replacement"),
        BRAKE_FLUID_CHANGE("Brake fluid replacement"),

        // Suspension and Steering
        SUSPENSION_REPAIR("Suspension system repair"),
        SHOCK_ABSORBER_REPLACEMENT("Shock absorber replacement"),
        STEERING_REPAIR("Steering system repair"),
        WHEEL_ALIGNMENT("Wheel alignment service"),
        WHEEL_BALANCING("Wheel balancing service"),

        // Tires
        TIRE_REPLACEMENT("Tire replacement"),
        TIRE_REPAIR("Tire repair service"),
        TIRE_ROTATION("Tire rotation service"),

        // Transmission
        TRANSMISSION_DIAGNOSIS("Transmission diagnosis"),
        TRANSMISSION_REPAIR("Transmission repair"),
        CLUTCH_REPLACEMENT("Clutch replacement"),

        // Electrical
        ELECTRICAL_DIAGNOSIS("Electrical system diagnosis"),
        BATTERY_REPLACEMENT("Battery replacement"),
        ALTERNATOR_REPAIR("Alternator repair"),
        STARTER_MOTOR_REPAIR("Starter motor repair"),
        LIGHTING_REPAIR("Vehicle lighting repair"),

        // Cooling System
        COOLING_SYSTEM_REPAIR("Cooling system repair"),
        RADIATOR_REPLACEMENT("Radiator replacement"),
        COOLANT_CHANGE("Coolant replacement"),

        // Air Conditioning
        AIR_CONDITIONING_SERVICE("Air conditioning maintenance"),
        AIR_CONDITIONING_REPAIR("Air conditioning repair"),

        // Exhaust
        EXHAUST_SYSTEM_REPAIR("Exhaust system repair"),
        CATALYTIC_CONVERTER_REPLACEMENT("Catalytic converter replacement"),

        // Diagnostics
        COMPUTER_DIAGNOSTIC("Computerized vehicle diagnostic"),
        VEHICLE_INSPECTION("General vehicle inspection"),

        // Bodywork and Paint
        BODYWORK_REPAIR("Bodywork repair"),
        PAINT_JOB("Automotive painting service"),

        // Other
        CUSTOM_WORK("Custom repair or modification"),
        OTHER("Other service");

        private final String description;

        EWorkOrderType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
}
