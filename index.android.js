"use strict";

import React from "react";
import { requireNativeComponent, Platform, View } from "react-native";

const ScanView = Platform.select({
  ios: View,
  android: requireNativeComponent("SimplyScanView"),
});

export default class SimplyScanView extends React.Component {
  render() {
    const { width = "100%", height = 200 } = this.props;
    return <ScanView {...this.props} style={{ width, height }} />;
  }
}
