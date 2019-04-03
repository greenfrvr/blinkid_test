import {createStackNavigator, createAppContainer} from 'react-navigation';
import {HomeScreen} from "./screens/home";
import {ScanScreen} from "./screens/scan";

const AppNavigator = createStackNavigator({
  Home: HomeScreen,
  Scan: ScanScreen
}, {
  initialRouteName: "Home"
});

export const AppContainer = createAppContainer(AppNavigator);