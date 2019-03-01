//package parking.Slot;
//
//public class Slot {
//
//    enum SlotStatus {
//        Available,
//        Occupied
//    }
//
//    SlotStatus status = SlotStatus.Available;
//    Vehicle vehicle = null;
//
//    Slot() {}
//
//    boolean assignVehicleIfFree(Vehicle vehicle) {
//        if(status == SlotStatus.Occupied) {
//            return false;
//        }
//        this.vehicle = vehicle;
//        return true;
//    }
//
//    boolean freeSlot() {
//        if(status == SlotStatus.Occupied) {
//            status = SlotStatus.Available;
//            return true;
//        }
//        return false;
//    }
//}
