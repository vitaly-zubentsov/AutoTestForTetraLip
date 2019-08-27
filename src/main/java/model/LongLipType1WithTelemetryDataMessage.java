package model;

import java.util.Map;

public class LongLipType1WithTelemetryDataMessage implements UDPMessage {

    private Map<Integer, Boolean> changeMap;
    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();

    private String SSI;
    private static String LONGITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LATITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LENGTH_IN_BITS = "00000000000000000000000100000000"; //в лонг лип типе 1 с данными телеметрии = 176 бит
    private static String PDU_HEADERS = "00001010";
    private static String PDU_TYPE = "01";
    private static String PDU_TYPE_EXTENSIONS = "0011";
    private static String TIME_DATA = "00";
    private static String LOCATION_SHAPE = "0101";
    private String longitude;
    private String latitude;
    private String horizontal_position_uncertainty;
    private String location_altitude;
    private static String VELOCITY_TYPE = "101";
    private String horizontal_velocity;
    private String direction_of_travel_extended;
    private String ACKNOWLEDGEMENT_REQUEST = "0";
    private static String TYPE_OF_Additional_DATA = "0";
    private String reason_for_sending;
    private static String TYPE_5_ELEMENT_IDENTIFIER = "00001";
    private static String TYPE_5_ELEMENT_LENGTH = "000000";
    private static String TYPE_5_ELEMENT_LENGTH_EXTENSION = "0001001";
    private static String TYPE_OF_MESSAGE_FROM_DEVICE = "01";
    private String type_of_device;
    private String power_supply_voltage_at_the_input_bpp;
    private String power_supply_voltage_at_the_control_console_input;

    private String power_supply_of_the_transmitter_output_amplifier_during_transmission;

    private String target_transmitter_power;
    private String transmitter_amplifier_output_temperature;
    private String swr_of_the_connected_antenna_at_the_operating_frequency;
    private String condition_hf_tract;
    private String audio_output_status;
    private String status_of_the_navigation_module;
    private String dry_contact_status;
    private String total_working_time_of_device;
    private String total_number_of_device_reloads;
    private String number_of_regular_device_reloads;
    private String operating_time_after_the_last_power_up;
    private String software_version_number;
    private String pcb_version_number;
    //private static String PDU_TAIL = "11"; // В дополнительном сообщении заполнение осуществляется единицами

    private String binSSI;

    private String binLongitude;

    private String binLatitude;
    private String binHorizontalPositionUncertainty;
    private String binLocationAltitude;
    private String binHorizontalVelocity;
    private String binDirectionOfTravelExtended;
    private String binReasonForSending;
    private String binTypeOfDevice;

    private String binPowerSupplyVoltageAtTheInputBPP;
    private String binPowerSupplyVoltageAtTheControlConsoleInput;
    private String binPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission;
    private String binTargetTransmitterPower;
    private String binTransmitterAmplifierOutputTemperature;
    private String binSWROfTheConnectedAntennaAtTheOperatingFrequency;
    private String binConditionHFTract;
    private String binAudioOutputStatus;
    private String binStatusOfTheNavigationModule;
    private String binDryContactStatus;
    private String binTotalWorkingTimeOfDevice;
    private String binTotalNumberOfDeviceReloads;
    private String binNumberOfRegularDeviceReloads;
    private String binOperatingTimeAfterTheLastPowerUp;
    private String binSoftwareVersionNumber;
    private String binPCBVersionNumber;

    public void initValuesFromUI() {
        binSSI = udpMessageHelper.convertDecStringNumberToBinStringNumber(SSI, 32);
        binLongitude = udpMessageHelper.calculateLipLongitudeFromDecLongitude(longitude);
        binLatitude = udpMessageHelper.calculateLipLatitudeFromDecLatitude(latitude);
        binHorizontalPositionUncertainty = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_position_uncertainty, 6);
        binLocationAltitude = udpMessageHelper.convertDecStringNumberToBinStringNumber(location_altitude, 12);
        binHorizontalVelocity = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_velocity, 7);
        binDirectionOfTravelExtended = udpMessageHelper.convertDecStringNumberToBinStringNumber(direction_of_travel_extended, 8);
        binReasonForSending = udpMessageHelper.convertDecStringNumberToBinStringNumber(reason_for_sending, 8);

        binTypeOfDevice = udpMessageHelper.convertDecStringNumberToBinStringNumber(type_of_device, 3);
        binPowerSupplyVoltageAtTheInputBPP = udpMessageHelper.convertDecStringNumberToBinStringNumber(power_supply_voltage_at_the_input_bpp, 7);
        binPowerSupplyVoltageAtTheControlConsoleInput = udpMessageHelper.convertDecStringNumberToBinStringNumber(power_supply_voltage_at_the_control_console_input, 7);
        binPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission = udpMessageHelper.convertDecStringNumberToBinStringNumber(power_supply_of_the_transmitter_output_amplifier_during_transmission, 7);
        binTargetTransmitterPower = udpMessageHelper.convertDecStringNumberToBinStringNumber(target_transmitter_power, 3);
        binTransmitterAmplifierOutputTemperature = udpMessageHelper.convertDecStringNumberToBinStringNumber(transmitter_amplifier_output_temperature, 8);
        binSWROfTheConnectedAntennaAtTheOperatingFrequency = udpMessageHelper.convertDecStringNumberToBinStringNumber(swr_of_the_connected_antenna_at_the_operating_frequency, 5);
        binConditionHFTract = udpMessageHelper.convertDecStringNumberToBinStringNumber(condition_hf_tract, 1);
        binAudioOutputStatus = udpMessageHelper.convertDecStringNumberToBinStringNumber(audio_output_status, 1);
        binStatusOfTheNavigationModule = udpMessageHelper.convertDecStringNumberToBinStringNumber(status_of_the_navigation_module, 1);
        binDryContactStatus = udpMessageHelper.convertDecStringNumberToBinStringNumber(dry_contact_status, 8);
        binTotalWorkingTimeOfDevice = udpMessageHelper.convertDecStringNumberToBinStringNumber(total_working_time_of_device, 16);
        binTotalNumberOfDeviceReloads = udpMessageHelper.convertDecStringNumberToBinStringNumber(total_number_of_device_reloads, 12);
        binNumberOfRegularDeviceReloads = udpMessageHelper.convertDecStringNumberToBinStringNumber(number_of_regular_device_reloads, 12);
        binOperatingTimeAfterTheLastPowerUp = udpMessageHelper.convertDecStringNumberToBinStringNumber(operating_time_after_the_last_power_up, 16);
        binSoftwareVersionNumber = udpMessageHelper.convertDecStringNumberToBinStringNumber(software_version_number, 7);
        binPCBVersionNumber = udpMessageHelper.convertDecStringNumberToBinStringNumber(pcb_version_number, 7);
    }

    public byte[] getUdpMessage() {
        String udpMessage = udpPacketsCounter.getPacket_counter() +
                binSSI +
                LONGITUDE_FROM_SERVER_OV +
                LATITUDE_FROM_SERVER_OV +
                LENGTH_IN_BITS +
                PDU_HEADERS +
                PDU_TYPE +
                PDU_TYPE_EXTENSIONS +
                TIME_DATA +
                LOCATION_SHAPE +
                binLongitude +
                binLatitude +
                binHorizontalPositionUncertainty +
                binLocationAltitude +
                VELOCITY_TYPE +
                binHorizontalVelocity +
                binDirectionOfTravelExtended +
                ACKNOWLEDGEMENT_REQUEST +
                TYPE_OF_Additional_DATA +
                binReasonForSending +
                TYPE_5_ELEMENT_IDENTIFIER +
                TYPE_5_ELEMENT_LENGTH +
                TYPE_5_ELEMENT_LENGTH_EXTENSION +
                TYPE_OF_MESSAGE_FROM_DEVICE +
                binTypeOfDevice +
                binPowerSupplyVoltageAtTheInputBPP +
                binPowerSupplyVoltageAtTheControlConsoleInput +
                binPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission +
                binTargetTransmitterPower +
                binTransmitterAmplifierOutputTemperature +
                binSWROfTheConnectedAntennaAtTheOperatingFrequency +
                binConditionHFTract +
                binAudioOutputStatus +
                binStatusOfTheNavigationModule +
                binDryContactStatus +
                binTotalWorkingTimeOfDevice +
                binTotalNumberOfDeviceReloads +
                binNumberOfRegularDeviceReloads +
                binOperatingTimeAfterTheLastPowerUp +
                binSoftwareVersionNumber +
                binPCBVersionNumber/* +
                PDU_TAIL*/;
        changeValuesOfElementsLipMessage();
        return udpMessageHelper.convertBinStringToByteArray(udpMessage);
    }


    public void changeValuesOfElementsLipMessage() {
        if (changeMap.get(0)) {
            binSSI = udpMessageHelper.addTheNumberToBinStringForSSI(binSSI, 1, 8000);
        }

        if (changeMap.get(1)) {
            binLongitude = udpMessageHelper.addTheNumberToBinString(binLongitude, 10, 33554431);
        }
        if (changeMap.get(2)) {
            binLatitude = udpMessageHelper.addTheNumberToBinString(binLatitude, 10, 16777215);
        }
        if (changeMap.get(3)) {
            binHorizontalPositionUncertainty = udpMessageHelper.addTheNumberToBinString(binHorizontalPositionUncertainty, 1, 7);
        }
        if (changeMap.get(4)) {
            binLocationAltitude = udpMessageHelper.addTheNumberToBinString(binLocationAltitude, 1, 2047);
        }
        if (changeMap.get(5)) {
            binHorizontalVelocity = udpMessageHelper.addTheNumberToBinString(binHorizontalVelocity, 1, 127);
        }
        if (changeMap.get(6)) {
            binDirectionOfTravelExtended = udpMessageHelper.addTheNumberToBinString(binDirectionOfTravelExtended, 1, 255);
        }
        if (changeMap.get(7)) {
            binReasonForSending = udpMessageHelper.addTheNumberToBinString(binReasonForSending, 1, 255);
        }
        if (changeMap.get(8)) {
            binTypeOfDevice = udpMessageHelper.addTheNumberToBinString(binTypeOfDevice, 1, 7);
        }
        System.out.println(binTypeOfDevice);
        if (changeMap.get(9)) {
            binPowerSupplyVoltageAtTheInputBPP = udpMessageHelper.addTheNumberToBinString(binPowerSupplyVoltageAtTheInputBPP, 1, 127);
        }
        if (changeMap.get(10)) {
            binPowerSupplyVoltageAtTheControlConsoleInput = udpMessageHelper.addTheNumberToBinString(binPowerSupplyVoltageAtTheControlConsoleInput, 1, 127);
        }
        if (changeMap.get(11)) {
            binPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission = udpMessageHelper.addTheNumberToBinString(binPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission, 1, 127);
        }
        if (changeMap.get(12)) {
            binTargetTransmitterPower = udpMessageHelper.addTheNumberToBinString(binTargetTransmitterPower, 1, 7);
        }
        if (changeMap.get(13)) {
            binTransmitterAmplifierOutputTemperature = udpMessageHelper.addTheNumberToBinString(binTransmitterAmplifierOutputTemperature, 1, 255);
        }
        if (changeMap.get(14)) {
            binSWROfTheConnectedAntennaAtTheOperatingFrequency = udpMessageHelper.addTheNumberToBinString(binSWROfTheConnectedAntennaAtTheOperatingFrequency, 1, 31);
        }
        if (changeMap.get(15)) {
            binConditionHFTract = udpMessageHelper.addTheNumberToBinString(binConditionHFTract, 1, 1);
        }
        if (changeMap.get(16)) {
            binAudioOutputStatus = udpMessageHelper.addTheNumberToBinString(binAudioOutputStatus, 1, 1);
        }
        if (changeMap.get(17)) {
            binStatusOfTheNavigationModule = udpMessageHelper.addTheNumberToBinString(binStatusOfTheNavigationModule, 1, 1);
        }
        if (changeMap.get(18)) {
            binDryContactStatus = udpMessageHelper.addTheNumberToBinString(binDryContactStatus, 1, 255);
        }
        if (changeMap.get(19)) {
            binTotalWorkingTimeOfDevice = udpMessageHelper.addTheNumberToBinString(binTotalWorkingTimeOfDevice, 100, 65535);
        }
        if (changeMap.get(20)) {
            binTotalNumberOfDeviceReloads = udpMessageHelper.addTheNumberToBinString(binTotalNumberOfDeviceReloads, 10, 4095);
        }
        if (changeMap.get(21)) {
            binNumberOfRegularDeviceReloads = udpMessageHelper.addTheNumberToBinString(binNumberOfRegularDeviceReloads, 10, 4095);
        }
        if (changeMap.get(22)) {
            binOperatingTimeAfterTheLastPowerUp = udpMessageHelper.addTheNumberToBinString(binOperatingTimeAfterTheLastPowerUp, 100, 65535);
        }
        if (changeMap.get(23)) {
            binSoftwareVersionNumber = udpMessageHelper.addTheNumberToBinString(binSoftwareVersionNumber, 1, 127);
        }
        if (changeMap.get(24)) {
            binPCBVersionNumber = udpMessageHelper.addTheNumberToBinString(binPCBVersionNumber, 1, 127);
        }

        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));
    }


    public LongLipType1WithTelemetryDataMessage withChangeMap(Map<Integer, Boolean> changeMap) {
        this.changeMap = changeMap;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withSSI(String SSI) {
        this.SSI = SSI;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withHorizontal_position_uncertainty(String horizontal_position_uncertainty) {
        this.horizontal_position_uncertainty = horizontal_position_uncertainty;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withLocation_altitude(String location_altitude) {
        this.location_altitude = location_altitude;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withHorizontal_velocity(String horizontal_velocity) {
        this.horizontal_velocity = horizontal_velocity;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withDirection_of_travel_extended(String direction_of_travel_extended) {
        this.direction_of_travel_extended = direction_of_travel_extended;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withReason_for_sending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withType_of_device(String type_of_device) {
        this.type_of_device = type_of_device;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withPower_supply_voltage_at_the_input_bpp(String power_supply_voltage_at_the_input_bpp) {
        this.power_supply_voltage_at_the_input_bpp = power_supply_voltage_at_the_input_bpp;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withPower_supply_voltage_at_the_control_console_input(String power_supply_voltage_at_the_control_console_input) {
        this.power_supply_voltage_at_the_control_console_input = power_supply_voltage_at_the_control_console_input;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withPower_supply_of_the_transmitter_output_amplifier_during_transmission(String power_supply_of_the_transmitter_output_amplifier_during_transmission) {
        this.power_supply_of_the_transmitter_output_amplifier_during_transmission = power_supply_of_the_transmitter_output_amplifier_during_transmission;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withTarget_transmitter_power(String target_transmitter_power) {
        this.target_transmitter_power = target_transmitter_power;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withTransmitter_amplifier_output_temperature(String transmitter_amplifier_output_temperature) {
        this.transmitter_amplifier_output_temperature = transmitter_amplifier_output_temperature;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withSwr_of_the_connected_antenna_at_the_operating_frequency(String swr_of_the_connected_antenna_at_the_operating_frequency) {
        this.swr_of_the_connected_antenna_at_the_operating_frequency = swr_of_the_connected_antenna_at_the_operating_frequency;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withCondition_hf_tract(String condition_hf_tract) {
        this.condition_hf_tract = condition_hf_tract;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withAudio_output_status(String audio_output_status) {
        this.audio_output_status = audio_output_status;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withStatus_of_the_navigation_module(String status_of_the_navigation_module) {
        this.status_of_the_navigation_module = status_of_the_navigation_module;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withDry_contact_status(String dry_contact_status) {
        this.dry_contact_status = dry_contact_status;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withTotal_working_time_of_device(String total_working_time_of_device) {
        this.total_working_time_of_device = total_working_time_of_device;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withTotal_number_of_device_reloads(String total_number_of_device_reloads) {
        this.total_number_of_device_reloads = total_number_of_device_reloads;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withNumber_of_regular_device_reloads(String number_of_regular_device_reloads) {
        this.number_of_regular_device_reloads = number_of_regular_device_reloads;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withOperating_time_after_the_last_power_up(String operating_time_after_the_last_power_up) {
        this.operating_time_after_the_last_power_up = operating_time_after_the_last_power_up;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withSoftware_version_number(String software_version_number) {
        this.software_version_number = software_version_number;
        return this;
    }

    public LongLipType1WithTelemetryDataMessage withPcb_version_number(String pcb_version_number) {
        this.pcb_version_number = pcb_version_number;
        return this;
    }
}
