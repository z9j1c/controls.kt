package hep.dataforge.control.api

import hep.dataforge.meta.MetaItem

/**
 * A hub that could locate multiple devices and redirect actions to them
 */
interface DeviceHub {
    fun getDevice(deviceName: String): Device?
}

suspend fun DeviceHub.getProperty(deviceName: String, propertyName: String): MetaItem<*> =
    (getDevice(deviceName) ?: error("Device with name $deviceName not found in the hub"))
        .getProperty(propertyName)

suspend fun DeviceHub.setProperty(deviceName: String, propertyName: String, value: MetaItem<*>) {
    (getDevice(deviceName) ?: error("Device with name $deviceName not found in the hub"))
        .setProperty(propertyName, value)
}

suspend fun DeviceHub.exec(deviceName: String, command: String, argument: MetaItem<*>?): MetaItem<*>? =
    (getDevice(deviceName) ?: error("Device with name $deviceName not found in the hub"))
        .exec(command, argument)