"use strict";
/* 
import React from "react";
import { requireNativeComponent, Platform, View } from "react-native";

export default function SimplyScan() {
  const Scan = requireNativeComponent("SimplyScanView");
  Scan.startScan();
} */

import { NativeModules } from "react-native";
module.exports = NativeModules.SimplyScanView;
